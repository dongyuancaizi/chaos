```@windows
git clone
cd chaos-code-generator/xxl-code-generator-admin
mvn clean package -Dmaven.test.skip=true
docker build -t  code-generator .
docker tag code-generator 39.100.194.142:8288/cui/code-generator
docker login  --username="admin" --password="Harbor12345" 39.100.194.142:8288
docker push 39.100.194.142:8288/cui/code-generator

cd chaos-admin
mvn clean package -Dmaven.test.skip=true
docker build -t chaos-admin .
docker tag chaos-admin 39.100.194.142:8288/cui/chaos-admin
docker push 39.100.194.142:8288/cui/chaos-admin
docker pull 39.100.194.142:8288/cui/chaos-admin

cd ??
mvn clean package -Dmaven.test.skip=true
docker build -t dubbo-admin .
docker tag dubbo-admin 39.100.194.142:8288/cui/dubbo-admin
docker push 39.100.194.142:8288/cui/dubbo-admin

cd chaos-third/xxl-job/xxl-job-admin
docker build -t xxljob .
docker tag xxljob 39.100.194.142:8288/cui/xxljob
docker push 39.100.194.142:8288/cui/xxljob
docker pull 39.100.194.142:8288/cui/xxljob
