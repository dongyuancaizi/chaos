机器A登录机器B
su gitlab-runner
ssh-keygen -t rsa
回车+3
验证 cd ~/.ssh
ssh-copy-id root@xx.xx.xxx.xx # 机器B的公网IP
输入 yes
ssh root@xx.xx.xxx.xx


：配置服务器之间的ssh登录
1.先在100服务器切换gitlab-runner用户：su gitlab-runner

    2.使用ssh-keygen -t rsa生成ssh的公钥和私钥：ssh-keygen -t rsa  #回车之后3次回车即可，你就会在 /home/gitlab-runner/.ssh目录下发现2个文件id_rsa.pub 和id_rsa

    3.然后再切换到root用户,重复上述操作,这样 root用户的ssh的公钥和私钥也生成了,接下来就是将gitlab-runner用户的公钥写入root用户的authorized_keys文件中

        $  su root

        $  ssh-keygen -t rsa

        $ cat /home/gitlab-runner/.ssh/id_rsa.pub >>/root/.ssh/authorized_keys

4.重启ssh：systemctl restart sshd

5.先切换到gitlab-runner用户 su gitlab-runner

6.使用ssh登录root用户 ssh root@192.168.57.100 -tt

你会发现你已经切换到了root用户了


 ssh-keyscan -H 39.100.194.142 >> /home/gitlab-runner/.ssh/known_hosts

