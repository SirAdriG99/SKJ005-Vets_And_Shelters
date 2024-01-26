cd ./back-office
./compile_front.sh
quarkus build --native
# mvn package -DskipTests
cp ./src/main/docker/Dockerfile.jvm ./Dockerfile-back-office
cd ../front-office
./compile_front.sh
quarkus build --native
# mvn package -DskipTests
cp ./src/main/docker/Dockerfile.jvm ./Dockerfile-front-office
cd ../
docker compose up