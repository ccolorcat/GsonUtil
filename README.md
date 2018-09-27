# GsonUtil

用于 json 解析，解决数据无默认值的问题，如需要 String 类型但对应的 json 数据为 null，此时可将 null 映射为空字符串，以减少 Java 中无处不在的非空判断。

使用前请详细了解自己项目的业务逻辑及需求，以确保下文中列举的数据映射不会带来其它负面影响。

## 1. 文件列表

| 文件名                           | 功能描述                                                     |
| -------------------------------- | ------------------------------------------------------------ |
| NullStringAdapterFactory         | 需要 String 类型，但对应的 json 数据为 null 时，将 null 解析为空字符串。 |
| NullArrayTypeAdapterFactory      | 需要数组时，但对应的 json 数据为 null 时，将 null 解析为空数组。 |
| NullCollectionTypeAdapterFactory | 需要集合（如 List）时，但对应的 json 数据为 null 时，将 null 解析为空集合（如空列表） |
| NullMultiDateAdapterFactory      | 需要 Date 类型，但对应的 json 数据为 null 时，将 null 解析为以当前时间建立的 Date 实例；json 数据不为 null 但存在多种格式时，根据设定的格式依次尝试（将对象转为 json 时使用设定的第一个格式进行转换）。 |
| NullDateAdapterFactory           | 作用同 NullMultiDateAdapterFactory，但只支持单一格式，不建议使用。 |
| JsonUtils                        | 全局的 Gson 工具类，Gson 线程安全，故无需建立多个实例。      |

## 2. 使用方法

(1) 在项目的 build.gradle 中配置仓库地址：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

(2) 添加项目依赖：

```groovy
dependencies {
    implementation 'com.github.ccolorcat:GsonUtil:v1.0.0'
}
```

## 3. 版本历史

v1.0.0

> 首次发布