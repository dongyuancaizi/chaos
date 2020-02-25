#Idea
[官网](https://www.jetbrains.com/)
[下载](https://www.jetbrains.com/idea/download/#section=windows)
[插件](https://plugins.jetbrains.com/idea)
***
## 快速使用
### 环境
*
*
*
### 安装
1.
2.
3.
### 命令
```

```
### 其他
修改
file->setting->file and code template

1.修改使用Eclipse风格的快捷键
File --> settings --> keymap --> 在下来框中选中Eclipse
2.单独设置一部分比较主要的与eclipse中不一致的操作
2.1自动补全Alt + /
File --> settings --> keymap 在搜索框中搜索 completion
2.2debug时运行选中代码片段Ctrl + shift + I
File --> settings --> keymap 在搜索框中搜索 evalue expression
代码类
Ctrl + F9	编译所有文件
Ctrl + shift + F9	编译有改动的文件
Ctrl + shift + 空格	对于喜欢写漂亮的文档注释的，可以通过Ctrl + shift + 空格 来预览Documentation
Ctrl + o	查看当前类的成员属性
Alt+ /	智能补全
Alt + Enter	相当于eclipse里Alt + /智能补全外的其他选项，或许还要更强大
Alt + Insert	生成代码的constructor override toString等等
组织导入：Ctrl+Shift+O
定位
行末/行首：End/Home
前一个/后一个单词：Ctrl+Right/Left
跳到某行：Ctrl+L
上下滚屏：Ctrl+Up/Down
上一个/下一个成员（成员对象或成员函数）：Ctrl+Shift+Up/Down
快速Outline：Ctrl+O
上一个编辑的位置：Ctrl+Q 
上一个/下一个光标的位置：Alt+Left/Right
选中 
选中到行末/行首：Shift+End/Home
选中上一个/下一个单词：Ctrl+Shift+Left/Right
选中闭合元素：Alt+Shift+Up/Down/Left/Right
编辑
删除行：Ctrl+D
删除下一个/上一个单词：Ctrl+Delete/Backspace
删除到行末：Ctrl+Shift+Delete
在当前行上插入一行：Ctrl+Shift+Enter
在当前行下插入一行： Shift+Enter
上下移动选中的行：Alt+Up/Down
上下移动选中的行或方法：Alt+Up/Down
注释：Ctrl+/
查找文件
Ctrl + F	当前文档查找
Ctrl + E/Ctrl + shift + E 最近文档查找
Ctrl + H	超级查找
Ctrl + T	查看实现类
Ctrl + shift+ T	搜索类
Ctrl + Alt +H	查看调用层次(显示一个方法的调用层次)
Ctrl + shift + R 打开文件
打开声明：F3
Ctrl+G 查看引用类
F4：打开选中元素的类型继承结构
重构
Ctrl + Alt + shift + T	超级重构，包含重构的常用功能
显示重构菜单：Alt+Shift+T
重构-改变方法签名：Alt+Shift+C
重构-移动：Alt+Shift+V
重构-重命名：Alt+Shift+R
Alt + shift + M	重构之修抽取方法
