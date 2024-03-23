resource "aws_vpc" "store-vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true
  tags = {
    Name        = "store-vpc"
    # Environment = "production"
  }
}

# create two private subnets
resource "aws_subnet" "private" {
  vpc_id            = aws_vpc.store-vpc.id
  count             = length(var.private_subnets)
  cidr_block        = element(var.private_subnets, count.index)
  availability_zone = element(var.availability_zones, count.index)

  tags = {
    Name        = "private-subnet-${count.index + 1}"
  }
}

# public subnet
resource "aws_subnet" "public" {
  vpc_id                  = aws_vpc.store-vpc.id
  cidr_block              = var.public_subnet
  # usa-east-1a
  availability_zone       = element(var.availability_zones, 0)
  # indicate that instances launched into the subnet should be assigned a public IP address
  map_public_ip_on_launch = true

  tags = {
    Name        = "APIG subnet"
  }
}

# To make a subnet public, you attach an internet gateway to your VPC 
# and add a route to the route table to send non-local traffic through the internet gateway to the internet (0.0.0.0/0
resource "aws_internet_gateway" "aws-igw" {
  vpc_id = aws_vpc.store-vpc.id
  tags = {
    Name        = "store-igw"
  }

}


resource "aws_route_table" "public" {
  vpc_id = aws_vpc.store-vpc.id

  tags = {
    Name        = "public-route-table"
  }
}


resource "aws_route_table_association" "public" {
  
  subnet_id      = aws_subnet.public.id
  route_table_id = aws_route_table.public.id
}

resource "aws_route" "public" {
  route_table_id         = aws_route_table.public.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.aws-igw.id
}

#  create an EIP 
resource "aws_eip" "gw" {
  domain = "vpc"      
  depends_on = [aws_internet_gateway.aws-igw]
}
# create the nat gateway 
resource "aws_nat_gateway" "gw" {
  subnet_id     = aws_subnet.public.id
  # Allocation ID of the Elastic IP address for the NAT Gateway.
  allocation_id = aws_eip.gw.id
}

# Create a new route table for the private subnets
# And make it route non-local traffic through the NAT gateway to the internet
resource "aws_route_table" "private" {
  vpc_id = aws_vpc.store-vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.gw.id
  }
}

# associate the newly created  private route table to the private subnets (so they don't default to the main route table)
resource "aws_route_table_association" "private" {
  # meta argument
  count          = length(var.private_subnets)
  subnet_id      = element(aws_subnet.private.*.id, count.index)
  route_table_id = aws_route_table.private.id
}