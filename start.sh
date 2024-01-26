cd ./back-office
./mvnw package
./compile_front.sh
quarkus build --native
# mvn package -DskipTests
cp ./src/main/docker/Dockerfile.jvm ./Dockerfile-back-office
cd ../front-office
./mvnw package
./compile_front.sh
quarkus build --native
# mvn package -DskipTests
cp ./src/main/docker/Dockerfile.jvm ./Dockerfile-front-office
cd ../
sudo docker compose up
