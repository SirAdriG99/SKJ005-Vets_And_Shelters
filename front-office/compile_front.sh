rm -rf ./front/dist/*
rm -rf ./src/main/resources/META-INF/resources/*
cd ./front
npm install
npm run build
cd ../
cp -a ./front/dist/. ./src/main/resources/META-INF/resources/