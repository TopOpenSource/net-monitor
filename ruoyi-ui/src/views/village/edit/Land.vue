<template>
  <el-row>
    <el-form :inline="true" :model="form" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="耕地面积" prop="pass">
        <el-input  autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="林地面积" prop="checkPass">
        <el-input ></el-input>
      </el-form-item>
      <el-form-item label="宅基地面积" prop="age">
        <el-input ></el-input>
      </el-form-item>

      <el-form-item label="企业用地" prop="age">
        <el-input ></el-input>
      </el-form-item>

      <el-form-item label="水井数量" prop="age">
        <el-input ></el-input>
      </el-form-item>

      <el-row>
        <el-form-item>
          <el-button type="primary" >保存</el-button>
        </el-form-item>
      </el-row>

      <el-row>
        <el-divider content-position="left">土地分布</el-divider>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-image :src="src" style="width: 300px; height: 200px" :preview-src-list="[src]">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
          </el-image>
        </el-col>

        <el-col :span="6">
          <el-image :src="src" style="width: 300px; height: 200px" :preview-src-list="[src]">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
          </el-image>
        </el-col>

        <el-col :span="6">
          <el-button type="primary">上传</el-button>
        </el-col>
      </el-row>

    </el-form>
  </el-row>
</template>
<script>
export default {
  data(){
    return {
      form:{},
      rules:{},
      src: 'https://imgs.699pic.com/images/503/230/719.jpg!detail.v1',
    }
  }
}
</script>
