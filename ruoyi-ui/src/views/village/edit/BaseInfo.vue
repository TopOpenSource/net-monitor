<template>
  <el-row v-loading="loading">
    <el-form :inline="true"  :model="form" status-icon :rules="rules" ref="form"  label-width="100px"
             class="demo-ruleForm">

      <el-form-item label="村名" prop="name">
        <el-input v-model="form.name"/>
      </el-form-item>

      <el-form-item label="人口数量" prop="villagerCount">
        <el-input-number v-model="form.villagerCount" :min="1"/>
      </el-form-item>

      <el-form-item label="户口数量" prop="familyCount">
        <el-input-number v-model="form.familyCount" :min="1"/>
      </el-form-item>

      <el-form-item label="支部书记" prop="admin">
        <el-input v-model="form.admin"/>
      </el-form-item>

      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="form.phone"/>
      </el-form-item>

      <el-row>
        <el-divider content-position="left">村容村貌</el-divider>

        <el-form-item label="" prop="imageIds">
          <div style="display: flex; flex-wrap: wrap; gap: 10px;">
            <!-- 每个图片项 -->
            <div v-for="imageId in form.imageIds" :key="imageId" style="position: relative; width: 300px; height: 200px;">
              <el-image :src="src" style="width: 100%; height: 100%;" :preview-src-list="[src]"></el-image>
              <el-button size="mini" type="text" icon="el-icon-delete" @click="handleImageDelete(imageId)" style="position: absolute; top: 10px; left: 10px;">删除</el-button>
            </div>
          </div>

          <el-upload :action="upload.action" :headers="upload.headers" :auto-upload="true"  :on-success="handleUploadSuccess" :show-file-list="false"	>
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-row>

      <el-row>
        <el-form-item>
          <el-button type="primary" @click="submitForm">保存</el-button>
        </el-form-item>
      </el-row>
    </el-form>
  </el-row>
</template>
<script>
import { getToken } from '@/utils/auth'
import {saveOrUpdate, getInfo} from "@/api/poor/village";
import imageIds from "core-js/internals/array-includes";
export default {
  data() {
    return {
      loading: false,
      form: {},
      rules:{
        name: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        admin: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        phone: [
          {required: true, message: "不能为空", trigger: "blur"}
        ]
      },
      upload:{
        action:process.env.VUE_APP_BASE_API +'/system/pub_file/upload',
        headers:{
          Authorization:'Bearer ' + getToken()
        }
      },
      src: 'https://p7.zbjimg.com/service/2017-03/08/service/58c00e36be1ea.jpg',
      srcList:[
        'https://p7.zbjimg.com/service/2017-03/08/service/58c00e36be1ea.jpg'
      ]
    }
  },
  props: {
    villageId: {
      type: String,
      default: null
    }
  },
  methods:{
    reset() {
      this.form = {
        name:'',
        villagerCount:0,
        familyCount:0,
        admin:'',
        phone:'',
        imageIds:[]
      };
      this.resetForm("form");
    },
    getBaseInfo(){
      this.reset()
      this.loading = true
      getInfo(this.villageId).then(res => {
        this.form = res
        console.log(this.form)
        this.loading = false
      })
    },
    submitForm(){
      this.loading = true
      this.$refs["form"].validate(valid => {
        if (valid) {
          saveOrUpdate(this.form).then(response => {
            this.loading = false
            this.$modal.msgSuccess("修改成功");
          })
        }else{
          this.loading=false
        }
      });
    },
    // 删除图片
    handleImageDelete(imageId){
      this.form.imageIds.splice(imageIds.indexOf(imageId), 1)
    },
    // 上传图片
    handleUploadSuccess(response, file, fileList){
      this.form.imageIds.push(response.data);
    }
  },
  created() {
    this.getBaseInfo()
  }
}
</script>
<style>


</style>
