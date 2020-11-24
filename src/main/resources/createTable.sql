create table Shapes (
    id varchar(50) not null primary key,
    name varchar(15),
    x1 numeric default 0,
    y1 numeric default 0,
    x2 numeric default 0,
    y2 numeric default 0,
    width numeric default 0,
    height numeric default 0,
    fillColor varchar(20) default "FFFFFF",
    borderColor varchar(20)  default "000000"
)