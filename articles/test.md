Title: Markdown test

#MD语法测试

将本文件下载，用自己的markdown编译器，编译，然后看结果，这里测试，如果显示正常则是支持，显示不正常则不支持。

* 基本语法
* 扩展语法
* Github扩展


##基本语法

一般来说，基本语法都是支持的，这里面也记录一下。做为样式查看使用。


说明，如果要在markdown里面输出以下字符，需要转义，前面加 `\` ,应该再熟悉不过了；

\\  \`  \* \_ \{\} \[\] \(\) \# \+ \- \. \!

###文字测试

>使用 `*` 或 `_` 为符号


全 `*` 号：  **粗的** *斜的* ***又粗又斜***
全 `_` 号：  __粗的__ _斜的_ ___又粗又斜___
混合：  _*粗的*_  __*又粗又斜*__

###标题测试

>H1标题 `#`
>
>H2标题 `##`
>
>H3标题 `###`
>
>H4标题 `####`
>
>H5标题 `#####`
>
>H6标题 `######`

#H1标题

##H2标题

###H3标题

####H4标题

#####H5标题

######H6标题

###嵌入HTML支持

>For any markup that is not covered by Markdown’s syntax, you simply use HTML itself. e.g. &lt;div&gt;,  &lt;table&gt;,  &lt;pre&gt;,  &lt;p&gt;,&lt;span&gt;, etc. 

不翻译，这里简单测试几个

<pre>
床前明月光
        DA
  HELLO  WORLD
</pre>
<span style="color:red;font-size:18px;">a</span><sup style="color:blue">2</sup> + <span style="color:red;font-size:18px;">b</span><sup style="color:blue">2</sup> = <span style="color:red;font-size:18px;">c</span><sup style="color:blue">2</sup>

<kdb>Ctrl</kdb> + <kdb style="color:blue;font-weight:bold">C</kdb>

###水平线
>水平线会变成 &lt;hr&gt;可以用三个以上的 `*` 或 `-` 组成

---

***

* * * 

- - - 

----------------

###引用
>在此行头加上 `>`,这就是一个引用

###列表
>有序列表 `数列` + `.` + `空格` 如 1. ,2. 等
>
>无序列表为 `*` 或 `+` 或 `-` + `空格`
>
#####无序列表 
* item1
* item2
* item3
+ item4

 text of item4
+ item5
- item6
- item7


#####有序列表

1. item1

 context of item1，kkktl.
2. item2
3. item3 

###代码片断

>若一行开头有4个空格或一个tab\(多行代码每行都要加\)，则认为此行为代码片段,会自动打上 &lt;pre&gt;&lt;code&gt; 标签，

    function fn(){
        alert('Hello World');
    }


###链接,图片
>链接表示为 `[ {链接文字} ]( {链接地址} "{链接的title属性}" )`，其中链接的title属性是可选的，链接地址可以是相对地址和绝对地址
>
>另外链接还可以定义，在文档的任何地方定义 `[{链接ID}]: {链接地址} "{链接的title属性}"`,<br>使用已定义的链接语法为 `[{链接文字}][{链接ID}]`


[home]: http://www.xdnote.com/ 
[xdgame]:http://xdgame.xdnote.com "xdGame"

链接到 [xdnote][home] , [xdgame][xdgame] , [xdnote](http://www.xdnote.com) , [xdgame] , [xdGame](http://xdgame.xdnote.com "xdGame")

>图片和链接差不多，前面加个`!`号,`![ {图片的Title属性} ]( {图片地址} "{图片的alt属性}" )`，图片也可以定义，定义方法与链接一样,图片的title属性可以为空，但中括号还是要有的

[pople]:http://xdgame.xdnote.com/resource/pople.png "ALT"

空图片：
![](http://xdgame.xdnote.com/resource/pople.png)
有title和alt的图片：
![Title][pople]


##扩展语法
刚接触markdown时，很多东西写不出来，于是各种搜索，最后发现基本的markdown标准就只定义以上的基本语法，写个table也不是不可以，定义为扩展语法，我用的markdownpad购买授权才支持~所以没办法只有花费15刀了。**本人不提倡破解，各位使用破解是可以的，但请不要宣扬盗版** ，这是做人的基本原则。

据[PHP Markdown Extra](https://michelf.ca/projects/php-markdown/extra/)介绍，发现扩展还挺多的。虽然实用的不多，而且我的markdownpad也不支持这么多，但还是小结下

###表格

基本大部分编译器和平台都提供了对表格table的支持。

<pre>
普通表格1
First Header  | Second Header
------------- | -------------
Content Cell  | Content Cell
Content Cell  | Content Cell
</pre>
First Header  | Second Header
------------- | -------------
Content Cell  | Content Cell
Content Cell  | Content Cell


<pre>
普通表格2
| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |
</pre>
| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |

表格左对齐，右对齐，居中
<pre>
第二行定义，在---左右加上冒号:
| Left-Aligned  | Center Aligned  | Right Aligned |
| :------------ |:---------------:| -----:|
| col 3 is      | some wordy text | $1600 |
| col 2 is      | centered        |   $12 |
| zebra stripes | are neat        |    $1 |
</pre>
| Left-Aligned  | Center Aligned  | Right Aligned |
| :------------ |:---------------:| -----:|
| col 3 is      | some wordy text | $1600 |
| col 2 is      | centered        |   $12 |
| zebra stripes | are neat        |    $1 |

###自定义标签属性
>在文字/图片/标题等元素的后面加上大括号，可以自定义元素的属性 id可简写为# class可简写为.

[xdnote](http://www.xdnote.com) {#linkid .linkclass target=_blank}

反正我这是编译不过了

###混合HTML
>在扩展中，允许markdown与html同时使用，但要在给标签上加入 `markdown="1"` 属性
<pre>
ep:
&lt;div markdown="1"&gt;
*Markdown*  **语法** ***文本***。
&lt;/div&gt;

&lt;table&gt;
&lt;tr&gt;
&lt;td markdown="1"&gt;*Markdown*  **语法** ***文本***。&lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;
</pre>
<div markdown="1">
*Markdown*  **语法** ***文本***。
</div>
<tr>
<td markdown="1">*Markdown*  **语法** ***文本***。</td>
</tr>
</table>

###词语解释

>写书常用，`{注释内容} [^{注释ID}]`

点击这段话右上角的1可以查看注释.[^1]

[^1]: 我是注释

###智能忽略下划线

>由于基本语法里面里面，用两个下划线 `_` 包住一个单词会判定为斜体，比如php方法里面的 array_diff_key 会把diff判定为斜体，有点强求，extra扩展会以单词为规范，根据 `_` 出现的位置去编译。


测试单词：array_diff_key , array_change_key_case 

**其实最好的方法还是不要用 `_` 全部用 `*` 就不存在了**

另外，省下的那些本人觉得不是很常用，就不写了，有兴趣的可以去[https://michelf.ca/projects/php-markdown/extra/](https://michelf.ca/projects/php-markdown/extra/) 自行参照

##Github扩展
Github又加入了一些新的特性，如果你的md文件是发布在github则可以用

###删除线
>用两个波浪线围住，如`~~删除~~`

~~删除~~

###代码高亮
<pre>
使用 三个 ` + {language}
基本可以说支持所有语言，比如

```javascript
fucntion test(){
    alert('Hello world');
}
```
</pre>

###自动生成链接
如果你的代码里面有个http链接，github会自动帮你生成a标签,如: http://www.xdnote.com/ 会自动变为[http://www.xdnote.com/](http://www.xdnote.com/)

###Emoji

Github支持emoji表情,如下

* http://emoji.muan.co/
* https://github.com/muan/emoji

:raised_hands:

:sweat:

:sleeping:

:grinning:

###另外，Github也支持扩展里面的，table,智能下划线