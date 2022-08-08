## Spring Microservices for Parts Management

## Config Server

Arquivo para guardar parâmetros de execução

## Initial Configuration


# Obter informação de serviço específico
No postman:
    GET -> http://{{host}}:{{config-port}}/client-service/dev

> Para obter dados sensitivos criptografados, deve-se habilitar a cripto simétrica
> POST -> http://{{host}}:{{config-port}}/encrypt
> Adicinar prefixo ** {cipher} ** para adicionar parametro criptografado no arquivo de propriedads


A chave de criptografia deve ser mantida em segredo, utilizando variáveis de ambiente carregadas:

    ENV CLIENT_SECRET={CIPHER_ENV}

Para carregar variaveis de ambiente:

    docker run --env PORT_NUMBER=8071 <image-name>
    docker-compose --env-file ./config/.env.dev up

Lembrando de atribuir a variável de ambiente com:

    export CLIENT_SECRET
    printenv | grep CLIENT_SECRET

Para executar o Server de configuração localmente pode-se executar o script:

> $ ./script/run_local.sh