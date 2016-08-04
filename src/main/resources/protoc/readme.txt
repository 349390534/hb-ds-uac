确认安装完成protocbuf编译环境完成之后操作
1：进入当前目录，在当前目录打开命令窗口（PS：按住shift键，鼠标左键，在此处打开命令窗口）
2：输入命令
protoc --java_out=$workspaces_dir\$mainresource_dir\ **.proto
PS:$workspaces_dir 为工作空间目录,$mainresource_dir为源代码目录
例如：E:\workspaces\howbuy\ec\data\howbuy-uac\src\main\resources\protoc>protoc --java_
out=E:\workspaces\howbuy\ec\data\howbuy-uac\src\main\java\ AppPvInfoZj.proto
其中$workspaces_dir指E:\workspaces\howbuy\ec\data\
 $mainresource_dir指howbuy-uac\src\main\java\
