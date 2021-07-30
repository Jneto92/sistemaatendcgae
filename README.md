# sistemaatendcgae

conceder previlégios totais para um usuario no mysql.(fonte: https://www.hostinger.com.br/tutoriais/como-criar-usuario-mysql-e-conceder-privilegios)
mysql> CREATE USER 'novo_usuário'@'localhost' IDENTIFIED BY 'senha';
mysql> GRANT ALL PRIVILEGES ON * . * TO 'novo_usuario'@'localhost';
mysql> FLUSH PRIVILEGES;
