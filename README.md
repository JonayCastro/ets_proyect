# ets_proyect

    Con esta aplicación un usuario puede crear una cuenta y añadir zapatillas de [FittestFreakest](https://www.fittestfreakest.es/zapatillas) de forma que periodicamente revisa una notificación por Telegram de cambios en el precios.

 ## Tecnologías

    La aplicación consta de dos partes, un fronted con las siguientes tecnologías:

- Angular 
- Angular material

    
        y una parte backend que consiste en una APIRest con:
 
 - Spring boot

## Persistencia
    Para la persistencia de los datos necesarios para el funcionamiento de la aplicación y los datos de usuarios y sus favoritos utilizamos una base de datos MySQL.

## Dependencias
    Las dependencias del backend están gestionadas por maven y las del front por node.js. 

### Instalación de dependencias:

- Front
    - Nos situamos dentro de la carpeta de front: `cd ~/ets_project/api/`
    - Instalamos dependecias: `npm i` o `npm install`

- Backend
    - Nos situamos dentro del carpeta de back: `cd ~/ets_proyect/app/`
    - Instalamos dependencias: `mvn clean install`

## Levantar el proyecto

### Docker

⚠️ Importante.
Hay que tener instalado en el sistema docker, y en el caso de windows una vez instalado, y siempre que queramos trabajar con docker, debemos tener levantada la aplicación Docker Desktop.

Tener en cuenta que si queremos levantar los modulos por separado en el caso del backend siempre estaremos obligados a levantar primero el contenedor de la base de datos.

Nos situamos en la ruta del proyecto `cd ~/ets_project/`

- Levantar el proyecto entero:

        Lanzamos el comando:
        
    `docker compose up` o `docker-compose up`

- Levantar solo la base de datos:

        Lanzamos el comando:

    `docker compose up mysqlserver` o `docker-compose up mysqlserver`

### Levantar cada módulo por separador

- Base de datos: `docker compose up mysqlserver` o `docker-compose up mysqlserver`
- Backend `mvn spring-boot:run`
- Frontend `npm run start`







