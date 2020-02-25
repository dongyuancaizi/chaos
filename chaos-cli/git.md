#Git
[官网](https://git-scm.com/)
[下载](https://git-scm.com/downloads)
https://gitee.com/all-about-git
[学习](https://oschina.gitee.io/learn-git-branching/)
***
## 快速使用
### 创建项目
```
打开 git bash
git config --global user.name "cui"
git config --global user.email "632381896@qq.com"
cd ~/.ssh
ssh-keygen -t rsa -C "632381896@qq.com"
```
```
登陆GitHub，打开“Account settings”，“SSH Keys”页面：
Add SSH Key--Title--Key--id_rsa.pub
```
```
mkdir demo
cd demo
git init
git status
cp xx/ ./
git add .
git commit -m "init demo"
```
```
在github创建git仓库
```
```
git remote add origin https://github.com/cui-stack/demo.git
git push -u origin master
```
```
git push origin master(二次提交)
```
### colne项目
```
git config --add compression=-1
git clone https://github.com/cui-stack/javademo.git
```
### 命令
```
git status  #查看暂存区状态
git diff    #查看文件变动
git log --pretty=oneline    # 查看提交日志
git reflog  # 查看命令日志
git checkout -- readme.txt  # 撤销工作区
git reset --hard HEAD^  # 撤销暂存区,重新放回工作区
git reset --hard commit_id
git reset --hard HEAD^  # 版本回退
git rm  # 删除文件
git branch -D <name>    # 强制删除分支
git branch  #展示分支
git branch <name> #创建分支
git checkout master  #切换分支
git checkout -b <name>  #创建+切换分支
git tag v1.0 
git tag v0.9 6224937
git tag 将tag列表展示
git show v0.9
git tag -a <tagname> -m "blablabla..."可以指定标签信息；
git tag -s <tagname> -m "blablabla..."可以用PGP签名标签；
git tag -d v0.1     #删除标签
git push origin master
git push origin v1.0
git push origin --tags
git push origin :refs/tags/v0.9 #远程删除标签
git merge <name>    #合并某分支到当前分支
git log --graph #分支合并图
git merge --no-ff -m "merge with no-ff" dev #禁用Fast forward模式
git stash
git stash list
git stash apply +git stash drop=git stash pop
git remote  #查看远程库信息
git remote -v
```
###删除远程目录
```
$ git pull origin master 将远程仓库里面的项目拉下来
$ dir  查看有哪些文件夹
$ git rm -r --cached target  删除target文件夹
$ git commit -m '删除了target'  提交,添加操作说明
$ git push -u origin master 将本次更改更新到github项目上去
```


