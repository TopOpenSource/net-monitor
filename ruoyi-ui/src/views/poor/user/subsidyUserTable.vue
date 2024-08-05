<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="type" label="补助类型" width="180">
      <template slot-scope="scope">
        <dict-tag :options="dict.type.subsidy_type" :value="scope.row.subsidyType"/>
      </template>
    </el-table-column>

    <el-table-column prop="subsidyDate" label="月份" width="180">
      <template slot-scope="scope">
        <span>{{ parseTime(scope.row.subsidyDate, '{y}-{m}') }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="money" label="金额（元）" width="180"></el-table-column>
  </el-table>
</template>
<script>

import {list} from  "@/api/poor/subsidy";
export default {
  name: "SubsidyUserTable",
  dicts:['subsidy_type'],
  props: {
    cardId: null,
  },
  data() {
    return {
      tableData:[

      ]
    };
  },
  created() {

  },
  mounted(){
     this.initData()
  },
  watch:{
    year(value,oldValue){
      if(value!=oldValue){
        this.initData()
      }
    }
  },
  methods: {
    initData(){
      list({cardId:this.cardId}).then(res=>{
        this.tableData=res
      })
    },
  }
};
</script>
<style scoped lang="scss">

</style>
