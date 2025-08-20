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


      <el-table-column
        prop="name"
        label="企业名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="admin"
        label="法人"ruoyi
        width="80">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系方式"
        width="180">
      </el-table-column>
      <el-table-column
        prop="capital"
        label="注册资金">
      </el-table-column>
      <el-table-column
        prop="code"
        label="企业信息代码">
      </el-table-column>
      <el-table-column
        prop="registDate"
        label="成立日期">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
      </el-table-column>

      <el-table-column label="图片"  width="180px" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-image  :src="imageUrl" style="max-width: 60px"  :preview-src-list="[imageUrl]">
          </el-image>
        </template>
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
      imageUrl:'https://q0.itc.cn/q_70/images03/20240617/45dfd91476f643c19567bb734debb6fb.png',
      tableData: [{
        name: 'XXX农业公司',
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
    }
  },
}
</script>
