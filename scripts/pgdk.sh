#!/bin/bash
#create new postgres docker container and connect to it

clear

echo "$0"

echo "$BASH_SOURCE"

echo "Going to financeApp folder..."
cd $(dirname $(dirname $BASH_SOURCE))
echo "pwd : $(pwd)"

echo


echo "docker ps -a"
docker ps -a

echo

read -p "----------------> Any container to stop ? (leave empty for no) " containerToStop;
if [ -z "$containerToStop" ]
then
    echo "No container stopped"
else
    echo "Stopping container $containerToStop"
    docker stop $containerToStop
fi

echo

echo "Listing content of current folder..."
ls

echo

read -p "----------------> Any folder to delete ? (leave empty for no) " folderToDelete;
if [ -z "$folderToDelete" ]
then
    echo "No folder deleted"
else
    echo "Deleting folder $folderToDelete"
    rm -rf $folderToDelete
fi

echo


read -p "----------------> What folder to use as Docker Volume for Postgres? (Default --> exit) " volumeName;

if [ -z "$volumeName" ]
then
    exit 1
fi

if [ -d "$(pwd)/$volumeName" ]
then
    echo "Re using $volumeName"
else
    mkdir $volumeName
    echo "Using new volume : $volumeName"
fi

echo

#read -p "----------------> What name to use for postgres container? (Default : pg-docker-test) " containerName;
if [ -z "$containerName" ]
then
    containerName="pg-docker-test"
fi
echo "Using container name : $containerName"

echo

read -p "----------------> What password to use for postgres container? (Default : docker) " psswd;
if [ -z "$psswd" ]
then
    psswd="docker"
fi
echo "Using postgres password : $psswd"

echo

read -p "----------------> What port to use for postgres container? (Default : 9432) " port;
if [ -z "$port" ]
then
    port="9432"
fi
echo "Using port : $port"

echo

echo "Resulting docker command"

echo "docker run --rm --name $containerName \
-e POSTGRES_PASSWORD=$psswd -d \
-p $port:5432 \
-v $(pwd)/$volumeName/:/var/lib/postgresql/data \
postgres"

echo

read -p "----------------> Execute? (Default : yes, anything : no) " decision;

if [ -z "$decision" ]
then
    echo "executing"
    docker run --rm --name $containerName \
-e POSTGRES_PASSWORD=$psswd -d \
-p $port:5432 \
-v $(pwd)/$volumeName/:/var/lib/postgresql/data \
postgres

    echo

    read -p "----------------> Connect to postgres? (Default : yes, anything : no) " connect;

    if [ -z "$connect" ]
    then
        echo sleeping 20
        sleep 20
        echo going on
        echo "psql \"dbname=postgres host=localhost user=postgres password=$psswd port=$port\""
        psql "dbname=postgres host=localhost user=postgres password=$psswd port=$port"
    else
        echo "not connecting"
    fi
else
    echo "not executing"
fi

echo