<template>
  <div class="app-container">
    <el-row>
      <el-tabs v-model="activeName">
        <el-tab-pane label="基本信息" name="baseInfo">
          <BaseInfo></BaseInfo>
        </el-tab-pane>
        <el-tab-pane label="重点人员" name="villager1">
          <Villager></Villager>
        </el-tab-pane>
        <el-tab-pane label="贫困人员" name="villager2">
          <Villager></Villager>
        </el-tab-pane>
        <el-tab-pane label="残疾人员" name="villager3">
          <Villager></Villager>
        </el-tab-pane>
        <el-tab-pane label="土地信息" name="land">
          <Land></Land>
        </el-tab-pane>
        <el-tab-pane label="危房信息" name="build">
          <Build></Build>
        </el-tab-pane>
        <el-tab-pane label="特色产业" name="industry">
          <Industry></Industry>
        </el-tab-pane>
      </el-tabs>

    </el-row>
  </div>
</template>


<script>
import BaseInfo from "@/views/village/edit/BaseInfo.vue";
import Build from "@/views/village/edit/Build.vue";
import Industry from "@/views/village/edit/Industry.vue";
import Land from "@/views/village/edit/Land.vue";
import Villager from "@/views/village/edit/Villager.vue";

export default {
  name: "VillageEdit",
  components: {BaseInfo, Build, Industry, Land, Villager},
  data() {
    return {
      activeName:'baseInfo',
    }
  }
}
</script>
