# Nucleoplasm

English (US) · [简体中文](README_szh.md)

<h4>Welcome to the Nucleoplasm mod! We are committed to super science fiction.</h4>

## The submodules contained in this branch

| Project                   | Completeness |
|:-------------------------:|:------------:|
| nucleoplasm\-biology      | 0%           |
| nucleoplasm\-chemistry    | 2%           |
| nucleoplasm\-default      | 1%           |
| nucleoplasm\-interstellar | 0%           |
| nucleoplasm\-json\-edit   | 10%          |
| nucleoplasm\-nuclide      | 1%           |

## nucleoplasm api
internally compatible quick-io sql</br>
internally compatible snake-yaml</br>
internally toml read and write</br>
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
biology in progress

## nucleoplasm chemistry
chemistry in progress

## nucleoplasm colony
Transplant mod of the Territory plugin

## nucleoplasm command line
Ported mod of permission group plugin

## nucleoplasm deprecated
Deprecated methods will be thrown into this

## nucleoplasm describer
Query the ported mod of the plug-in

## nucleoplasm economy
Ported mod of the economy plugin

## nucleoplasm feature repair
Bug fix tool, in development, original version or mod

## nucleoplasm interstellar
Stargate plug-in re-engraving, along with interstellar mod

## nucleoplasm normandy login
Login plugin fork

## nucleoplasm nuclide
nuclide module core

## globals

- [ ] Machine
    - [ ] Proton Separator Machine
    - [ ] Proton Exchange Machine
    - [ ] Neutron Separator Machine
    - [ ] Neutron Exchange Machine
- [ ] Block & Item
    - [ ] Periodic Table of Elements
    - [ ] Nuclide
    - [ ] Chemical Compound
    - [ ] Covalent Bond
    - [ ] Organic Compound
    - [ ] Electrodynamics
    - [ ] Quantum Mechanics
    - [ ] Disintegration
- [ ] Extends Taskline
    - [ ] Server side plugin support
    - [ ] chemistry
    - [ ] biology
    - [ ] Railway Project
    - [ ] Interstellar Project

