create table users_folowwers (id_usuario int,
							  Id_seguidor int,
                              constraint fk_userUserFollower foreign key(id_usuario)references User(id));