<template>
  <el-row>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" >新增</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="头像" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-image  :src="imageUrl" style="max-width: 60px;height: auto" :preview-src-list="[imageUrl]">

          </el-image>

        </template>
      </el-table-column>

      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="sex" label="性别" width="180"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>
      <el-table-column prop="address" label="住址"></el-table-column>
      <el-table-column prop="reason" label="事由"></el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" >修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      imageUrl:'https://n.sinaimg.cn/sinakd20121/640/w1920h1920/20221023/1384-9b10dd14ad24b0b1ce94456adab13438.jpg',
      tableData: [{
        name: '王小虎',
        age: 50,
        sex:'男',
        phone:'0123456789',
        address:'XX村X号',
        reason:'土地纠纷'
      }, {
        name: '王小虎',
        age: 50,
        sex:'男',
        phone:'0123456789',
        address:'XX村X号',
        reason:'土地纠纷'
      }]
    }
  },
  props: {
    villageId: {
      type: String,
      default: null
    },
    type:{
      type: String,
      default: null
    }
  },
}
</script>
