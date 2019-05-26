CREATE TABLE config_properties (
    id          int(10)         NOT NULL AUTO_INCREMENT, 
    key1        varchar(50)     NOT NULL, 
    value1      varchar(500)    DEFAULT '', 
    application varchar(50)     NOT NULL, 
    profile     varchar(50)     NOT NULL, 
    label       varchar(50)     DEFAULT NULL, 
    PRIMARY KEY (id)
);

insert into config_properties(key1, value1, application, profile, label) 
    values('server.port', '8080', 'config-client', 'dev', 'master');
insert into config_properties(key1, value1, application, profile, label) 
    values('foo', 'bar-jdbc', 'config-client', 'dev', 'master');
insert into config_properties(key1, value1, application, profile, label) 
    values('server.port', '8090', 'mcd-config', 'test', 'master');
insert into config_properties(key1, value1, application, profile, label) 
    values('foo', 'bar-jms', 'mcd-config', 'test', 'master');
