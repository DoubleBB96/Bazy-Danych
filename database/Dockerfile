FROM library/mysql:5.7.21

COPY setup.sql /docker-entrypoint-initdb.d/
COPY vb7.sql /docker-entrypoint-initdb.d/

COPY data/* /var/lib/mysql-files/
