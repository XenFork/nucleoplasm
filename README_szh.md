# Nucleoplasm

[English (US)](README.md) · 简体中文

<h4>欢迎使用核质模组！我们将致力于科幻的极致。</h4>

## sub projects

- [x] nucleoplasm-api&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [ ] nucleoplasm-nuclide&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;&ensp;&ensp;0%
- [ ] nucleoplasm-chemistry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [ ] nucleoplasm-feature-repair&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [ ] nucleoplasm-interstellar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [x] nucleoplasm-command-level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [x] nucleoplasm-normandy-login&nbsp;&nbsp;&emsp;&emsp;1.2%
- [ ] nucleoplasm-colony&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;0%
- [ ] nucleoplasm-describer&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0%
- [ ] nucleoplasm-economy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&ensp;&ensp;0%

## nucleoplasm api
内部兼容 quick-io数据库</br>
内部兼容 snake-yaml</br>
内部兼容 toml读写</br>
#### 使用教学
```groovy
repositories {
  maven { url 'https://chinawaremc.github.io/maven-repo/' }
}
dependencies {
  modImplementation("io.github.xenfork:nucleoplasm-api-fabric:$nucleoplasm_api_dependencies_version-${minecraft_version}")
  implementation("com.github.artbits:quickio:1.3.2")
  implementation("com.moandjiezana.toml:toml4j:0.7.2")
  implementation("org.yaml:snakeyaml:2.0")
}
```

## nucleoplasm biology
生物学实现中

## nucleoplasm chemistry
化学实现中

## nucleoplasm colony
领地插件的移植mod

## nucleoplasm command line
权限组插件的移植mod

## nucleoplasm deprecated
已经废弃的方法会丢进这个里面

## nucleoplasm describer
查询插件的移植mod

## nucleoplasm economy
经济插件的移植mod

## nucleoplasm feature repair
bug修复利器，开发中，原版或者模组

## nucleoplasm interstellar
星门插件复刻，顺带星际mod实现

## nucleoplasm normandy login
登录插件复刻

## nucleoplasm nuclide
核素模组核心

## 全局

- [ ] 机器
    - [ ] 质子分离机
    - [ ] 质子置换机
    - [ ] 中子分离机
    - [ ] 中子置换机
- [ ] 方块与物品
    - [ ] 元素周期表
    - [ ] 核素
    - [ ] 化合物
    - [ ] 共价键
    - [ ] 有机化合物
    - [ ] 电力学
    - [ ] 遇事不决____!
    - [ ] 衰变
- [ ] 拓展线路
    - [ ] 服务端插件支持
    - [ ] 化学
    - [ ] 生物学
    - [ ] 铁路工程
    - [ ] 星际
