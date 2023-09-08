# Nucleoplasm

[![Gradle Package](https://github.com/XenFork/nucleoplasm/actions/workflows/gradle-publish.yml/badge.svg?branch=forge-fabric-1.20.1-main&event=milestone)](https://github.com/XenFork/nucleoplasm/actions/workflows/gradle-publish.yml)
[![Latest Commit](https://img.shields.io/github/last-commit/XenFork/nucleoplasm)](https://github.com/XenFork/nucleoplasm/commits/forge-fabric-1.20.1-main)
![GitHub top language](https://img.shields.io/github/languages/top/XenFork/nucleoplasm)
![GitHub language count](https://img.shields.io/github/languages/count/XenFork/nucleoplasm)
[![GitHub](https://img.shields.io/github/license/XenFork/nucleoplasm)](LICENSE)

[![English (United States)](https://img.shields.io/badge/language-en_us-red)](README.md)
[![日本語](https://img.shields.io/badge/%E8%A8%80%E8%AA%9E-%E6%97%A5%E6%9C%AC%E8%AA%9E-grey)](README_sja_jp.md)

<h4>欢迎使用核质模组！我们将致力于科幻的极致。</h4>

## 进度

- [x] nucleoplasm ------ 0%

## 名言
著名的sf先生曾经说过浪漫是第一生产力

## 说明
1. 对于能源方面
- 1. 动力转轴用rpm(现实齿轮旋转单位)齿轮大小设计不定也可以为r/s
- 2. 通过动能，势能或者热能转电能(蒸汽机,潮汐能，地热能，太阳能，核能散热)
- 3. 电路分多种情况，电压V 电流A 电阻R
- 4. 未来的实现:网孔回路(基尔霍夫电压 基尔霍夫电流)
2. 受到零件工艺影响，齿轮带磨损
## 计划
- 生产工艺
  - 手搓
    - 第一阶段：手搓，需要从地上捡起碎石头，找到一块较大的石头，右键上下滑动，来打磨碎石子成锋利的石刀
    - 要注意的是，过度的打磨会产生反效果，过度的锋利会导致石刀绷断
    - 然后拿打磨后的碎石块，去切草，概率出坚韧的草片，只有这种草片才能制作坚韧的草绳
    - 草片只能做草绳耐久值恒定为1，损坏后会散架，返还打磨后的碎石块，和概率返还木棍
    - 草绳+碎石块+木棍无序合成->切割工具刀(碎石)
    - 碎石(nbt 随着打磨影响) 打草也会产生磨损，没有合成切割工具刀(碎石)的碎石对工艺处理时会扣血
    - 第二阶段：拿切割工具刀进行加工，可加工物件为
      - 砧板(原木)
    - 对砧板加工产物有:
      - 去皮原木 (切割工具刀左键) 
      - 木板 (1个原木 2个木板)
      - 木棍 (1个木板 1个木棍)
    - 切割工具刀倍率计算(百分比)[参考的莫氏硬度，由于是mc所以不太可能完全实现，只能说尽量贴近]() [后面的补充部分为维氏硬度单位]()

|  **材料**  | **莫氏硬度** | **维氏硬度** | ** 摩氏硬度 ** |
|:--------:|:--------:|:--------:|:----------:|
|    滑石    |    1     |          |            |
|    石墨    |    1     |          |            |
|    砒霜    |   1.5    |          |            |
|    石膏    |    2     |          |            |
|    冰块    |   2~3    |          |            |
|    指甲    |   2.5    |          |            |
|    琥珀    |   2.5    |          |            |
|    象牙    |   2.5    |          |            |
|    黄金    |  2.5~3   |          |            |
|    银     |  2.5~3   |          |            |
|    铝     |  2.5~3   |          |            |
|   方解石    |    3     |          |            |
|    铜     |    3     |          |            |
|    珍珠    |    3     |          |            |
|    贝壳    |   3.5    |          |            |
|    萤石    |    4     |          |            |
|    铂金    |  4~4.5   |          |            |
|    铁     |   4~5    | 30~80HV5 |            |
|    碳钢    |          | 30~80HV5 |            |
|   磷灰石    |    5     |          |            |
|   不锈钢    |   5.5    |          |            |
|   正长石    |    6     |          |            |
|   丹泉石    |    6     |          |            |
|   坦桑石    |    6     |          |            |
|    纯钛    |    6     |          |            |
|    软玉    |  6~6.5   |          |            |
|  新疆-阎玉   |  6~6.5   |          |            |
|   黄铁矿    |   6.5    |          |            |
| 牙齿(齿冠外层) |   6~7    |          |            |
|    硬玉    |   6~7    |          |            |
|   缅甸翡翠   |  6.5~7   |          |            |
|    翠玉    |  6.5~7   |          |            |
|    玻璃    |   6.5    |          |            |
|    石英    |    7     |          |            |
|   紫水晶    |    7     |          |            |
|   电气石    |   7.5    |          |            |
|   石榴子石   |   7~8    |          |            |
|    黄玉    |    8     |          |            |
|   金绿柱石   |   8.5    |          |            |
|    刚玉    |    9     |          |            |
|    铬     |    9     |          |            |
|    钨钢    |    9     |          |            |
|    钻石    |    10    |          |            |
| 聚合钻石纳米棒  |   >10    |          |            |
|  四氮化三碳   |   >10    |          |            |

# warn 核能受发热影响，温度越低产能越低，对散热的要求也高

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
    - [ ] 化学
    - [ ] 生物学
    - [ ] 铁路工程
    - [ ] 星际

