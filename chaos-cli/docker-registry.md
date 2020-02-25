@docker
docker run -d -p 5000:5000 -v /data:/var/lib/registry --restart=always --name registry registry:2

http-https问题
@windows
docker-machine ssh
sudo sed -i "s|EXTRA_ARGS='|EXTRA_ARGS='--insecure-registry=39.100.194.142:5000 |g" /var/lib/boot2docker/profile
exit
docker-machine restart

@linux
{ "insecure-registries":["39.100.194.142:5000"] }
systemctl daemon-reload
systemctl restart docker.service
systemctl enable docker.service

docker push 39.100.194.142:5000/cui/chaos-admin
curl -X GET --insecure  http://39.100.194.142:5000/v2/_catalog
