create sequence hibernate_sequence start 1 increment 1;

    create table "Admin" (
        idAdmin int4 not null,
        username varchar(255) not null,
        creationdate date,
        email varchar(255),
        lastname varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (idAdmin, username)
    );

    create table "Articulo" (
        idArt int4 not null,
        descripcion varchar(255),
        fechaPublicado timestamp,
        nombre varchar(255),
        precio float4 not null,
        idCategoria int4,
        primary key (idArt)
    );

    create table "Categoria" (
        idCategoria int4 not null,
        descripcion varchar(255),
        nombre varchar(255),
        primary key (idCategoria)
    );

    create table "Cliente" (
        idCliente int4 not null,
        username varchar(255) not null,
        creationdate date,
        email varchar(255),
        lastname varchar(255),
        name varchar(255),
        password varchar(255),
        codigoPostal int4 not null,
        direccion varchar(255),
        primary key (idCliente, username)
    );

    create table "Stock" (
        cantidad int4 not null,
        idArt int4 not null,
        primary key (idArt)
    );

    create table Tenant (
        id int4 not null,
        nameunit varchar(255),
        url varchar(255),
        usado boolean not null,
        username varchar(255),
        primary key (id)
    );

    alter table "Articulo" 
        add constraint FKdqsmhp9vf4kfn1rwdkkql2t2y 
        foreign key (idCategoria) 
        references "Categoria";

    alter table "Stock" 
        add constraint FKhdhgvckdgq0wp0h37m9nnxh0x 
        foreign key (idArt) 
        references "Articulo";
