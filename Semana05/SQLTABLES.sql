/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  kaue_brito
 * Created: 28/08/2023
 */
create type tipo_pessoa as ENUM ('PF', 'PJ');

create table pessoa(
	id UUID primary key,
	nome VARCHAR(100) not null,
	documento VARCHAR(18) not null UNIQUE,
	tipo tipo_pessoa not null
	
);
select * from conta
create type tipo_conta as ENUM ('corrente', 'salario', 'poupança');
create table conta(
	id UUID primary key,
	numero integer not null unique,
	saldo decimal(15,2) not null default(0.0),
	tipo tipo_conta not null,
	pessoa_id uuid References pessoa(id)
)
)
