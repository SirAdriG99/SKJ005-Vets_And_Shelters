# rm -rf ./front/dist/*
# rm -rf ./src/main/resources/META-INF/resources/*
cd ./front
npm install
# npm install axios
npm run build
cd ../
cp -a ./front/dist/. ./src/main/resources/META-INF/resources/