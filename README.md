# AndroidLintPlus

A demo to custom your Lint rules

#中文
扩展Lint 规则，该库写了三个场景

1、JavaChineseStringDetector：检查 java 代码中的中文字符串，有些团队的开发规范里规定中文字符串必须放到 string.xml里。

2、LayoutFileNameDetector：检查布局文件的文件名，你可以在这个类里定义一套自己团队的命名规范的正则表达式。强制整个团队的命名统一。

3、ViewIdDetector：检查布局文件里 view 的 id，同上上一条，你也可以定义一套自己的正则。

4、用法见下文，顺便提一下你可以把定制后的 lint task加到 build 过程里来强制规范代码，如果 lint 失败则 build 失败。你还可以集成到[AndroidCodeQuality](https://github.com/MasonLiuChn/AndroidCodeQuality)
使用checkstyle,pmd,findbug共同提高代码质量。

###最后一句：程序员何苦为难程序员呢

# Feature
1.JavaChineseStringDetector:check chinese string in java file.

2.LayoutFileNameDetector:check xml file name in layout folder.

3.ViewIdDetector:check view id name in layout xml file.

4.custom your own rule in JavaChineseStringDetector.java,LayoutFileNameDetector.java,ViewIdDetector.java

#Usage

1.go to the module 'library_java' and run '../gradlew clean install'

2.config which Detector to use
```groovy
android {
    ...
    lintOptions {
        abortOnError true
        textReport true
        //checkAllWarnings true
        check 'ViewId','JavaChineseString','LayoutFileName'
    }
}
```

3.run './gradlew lint' at project root

4.you can config this to the build task,when the check rule error,stop build task. you can integrate this into [AndroidCodeQuality](https://github.com/MasonLiuChn/AndroidCodeQuality)

#To go further

This project is based on these two other projects, which are awesome. Consider take a look at them :
 - [custom-lint-rules](http://jeremie-martinez.com/2015/12/15/custom-lint-rules/).
 - [linette](https://github.com/bignerdranch/linette).

#Contact me:

- Blog:http://blog.csdn.net/masonblog

- Email:MasonLiuChn@gmail.com

#License:
    Copyright 2016 MasonLiu, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
