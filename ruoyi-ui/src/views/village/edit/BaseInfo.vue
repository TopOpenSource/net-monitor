<template>
  <el-row>
    <el-form :inline="true" :model="form" status-icon :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="村名" prop="pass">
        <el-input autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="村支书" prop="checkPass">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="age">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="重点人员数量" prop="age">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="特色产业" prop="age">
        <el-input></el-input>
      </el-form-item>

      <el-row>
        <el-form-item>
          <el-button type="primary">保存</el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <el-row>
      <el-divider content-position="left">村容村貌</el-divider>
    </el-row>
      <el-row>
        <el-col :span="6">
          <el-image :src="src" style="width: 300px; height: 200px" :preview-src-list="srcList">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
          </el-image>
        </el-col>

        <el-col :span="6">
          <el-image :src="src" style="width: 300px; height: 200px" :preview-src-list="srcList">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
          </el-image>
        </el-col>

        <el-col :span="6">
          <el-button type="primary">上传</el-button>
        </el-col>
      </el-row>

  </el-row>
</template>
<script>
export default {
  data() {
    return {
      form: {},
      rules: {},
      src: 'https://p7.zbjimg.com/service/2017-03/08/service/58c00e36be1ea.jpg',
      srcList:[
        'https://p7.zbjimg.com/service/2017-03/08/service/58c00e36be1ea.jpg'
      ]
    }
  }
}
</script>
