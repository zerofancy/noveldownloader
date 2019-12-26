# noveldownloader
一个用Java+selenium写的小说下载器，基本原理就是模仿用户不断点“下一页”

## Selenium 是什么？

> Selenium automates browsers. That's it! What you do with that power is entirely up to you. Primarily, it is for automating web applications for testing purposes, but is certainly not limited to just that. Boring web-based administration tasks can (and should!) be automated as well.

> Selenium has the support of some of the largest browser vendors who have taken (or are taking) steps to make Selenium a native part of their browser. It is also the core technology in countless other browser automation tools, APIs and frameworks.

`Selenium`是一个用于Web应用程序测试的工具。`Selenium`测试直接运行在浏览器中，就像真正的用户在操作一样。支持的浏览器包括IE（7, 8, 9, 10, 11），`Mozilla Firefox`，`Safari`，`Google Chrome`，`Opera`等。这个工具的主要功能包括：测试与浏览器的兼容性——测试你的应用程序看是否能够很好得工作在不同浏览器和操作系统之上。测试系统功能——创建回归测试检验软件功能和用户需求。支持自动录制动作和自动生成 .Net、Java、Perl等不同语言的测试脚本。

## 思路
很多小说网站展示的布局是比较固定的，通过不断点击“下一页”就可以看到所有章节。这就使得我们可以通过selenium根据xpath选择元素自动点击下一页抓取。

`pandoc`定义的特殊格式markdown文档可以直接转换成epub等多种格式，这样就可以在手机上观看，还解决了目录索引的问题。


## 使用方法
1. 运行
2. 填写信息，标题作者等不用说，注意填写后回车一次，不要多回车，不然会出错。
3. 标题`xpath`等，在小说网站按`F12`，利用浏览器工具可以直接复制`xpath`
![利用浏览器工具复制xpath](/image/xpath.png "利用浏览器工具复制xpath")

4. 文件名，建议以`.md`结尾，要求输入全路径。
5. 等待下载结束
6. 利用pandoc将md文件转换为epub电子书

总体流程：
```
请输入书的标题。
异常生物见闻录
请输入书的作者。
远瞳
请输入书的首章url。
http://www.shuquge.com/txt/1197/723676.html
请输入出错后重试的次数。
5
请输入标题xpath。
//*[@id="wrapper"]/div[4]/div[2]/h1
请输入内容xpath。
//*[@id="content"]
请输入下一页xpath。
//*[@id="wrapper"]/div[4]/div[2]/div[3]/ul/li[3]/a
请输入文件名（包括路径）。
E:\异常生物见闻录.md
Starting ChromeDriver 73.0.3683.68 (47787ec04b6e38e22703e856e101e840b65afe72) on port 16551
Only local connections are allowed.
Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
五月 10, 2019 1:43:52 下午 org.openqa.selenium.remote.ProtocolHandshake createSession
信息: Detected dialect: OSS
Downloading>第一章 目前为止很正常
Downloading>第二章 然后一下子就不正常了！
Downloading>第三章 狼
Downloading>第四章 狼人秘术
Downloading>第五章 还有猫
Downloading>第六章 拜托，你是只狼人好么？
Downloading>第七章 光有第六感管蛋用
Downloading>第八章 狼与蝙蝠
Downloading>第九章 不正常生物
Downloading>第十章 穷酸吸血鬼
Downloading>第十一章 神秘来电
Downloading>第十二章 偏远的小村
Downloading>第十三章 “面试”地点
Downloading>第十四章 工作单位高大上
Downloading>第十五章 神仙招工那点事
……省略……
Downloading>第一千七百六十章 女神的声音
Downloading>第一千七百六十一章 神意就是神意
Downloading>第一千七百六十二章 撤离行动
Downloading>第一千七百六十三章 守夜人部队
Downloading>第一千七百六十四章 最后一个计划
Downloading>第一千七百六十五章 决战之日
Downloading>第一千七百六十六章 进入黑暗
Downloading>第一千七百六十七章 各自的战场
Downloading>第一千七百六十八章 战场翻书
Downloading>第一千七百六十九章 献祭舰娘的腰子
Downloading>第一千七百七十章 直面疯嚣
Downloading>第一千七百七十一章 惊不惊喜
Downloading>第一千七百七十二章 意不意外
Downloading>第一千七百七十三章 弑神者（终章）
Downloading>新书《黎明之剑》已经发布
未找到内容，正在重试……
未找到内容，正在重试……
未找到内容，正在重试……
未找到内容，正在重试……
未找到内容，正在重试……
OK!
```
pandoc命令：
```
pandoc 异常生物见闻录.md -o 异常生物见闻录.epub
```

于是就成功了：

![小说截图](/image/novel.png "小说截图")

目录的显示也没问题：

![小说目录](/image/novel_index.png "小说目录")

## 参考
[本人的博客——Java selenium 下载网络小说
](https://www.ntutn.top/show/40)

## License
MIT:[https://rem.mit-license.org/](https://rem.mit-license.org/)