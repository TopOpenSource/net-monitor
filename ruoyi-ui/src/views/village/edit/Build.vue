<template>
  <el-row>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" >新增</el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%">

      <el-table-column label="图片" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-image  :src="imageUrl1" style="max-width: 60px;height: auto" :preview-src-list="[imageUrl1,imageUrl2,imageUrl3]">
          </el-image>
        </template>
      </el-table-column>

      <el-table-column
        prop="name"
        label="户主"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="具体位置">
      </el-table-column>
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
      imageUrl1:'http://www.jiaguhome.com/Uploads/Picture/2022-02/6209bd210ea0a.jpg',
      imageUrl2:'https://tgi13.jia.com/130/140/30140769.jpg',
      imageUrl3:'https://tgi1.jia.com/129/140/29140363.jpg',
      tableData: [{
        name: '王小虎',
        phone:'0123456789',
        address:'XX村X号'
      }, {
        name: '王小虎',
        phone:'0123456789',
        address:'XX村X号'
      }]
    }
  }
}
</script>
