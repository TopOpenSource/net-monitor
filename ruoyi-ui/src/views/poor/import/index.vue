<template>
  <div class="app-container">
    <el-row :gutter="12">
      <el-col :span="8">
        <el-card shadow="always">
          <div slot="header" class="clearfix">
            <span>低保补贴</span>
          </div>

          <el-form ref="form" label-width="80px">
            <el-form-item label="补贴月份">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>

            <el-form-item>
              <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传xls/xlx文件</div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">导入</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card shadow="always">
          <div slot="header" class="clearfix">
            <span>特困补贴</span>
          </div>

          <el-form ref="form" label-width="80px">
            <el-form-item label="补贴月份">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>

            <el-form-item>
              <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传xls/xlx文件</div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">导入</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>


      <el-col :span="8">
        <el-card shadow="always">
          <div slot="header" class="clearfix">
            <span>伤残补贴</span>
          </div>

          <el-form ref="form" label-width="80px">
            <el-form-item label="补贴月份">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>

            <el-form-item>
              <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传xls/xlx文件</div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">导入</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card shadow="always">
          <div slot="header" class="clearfix">
            <span>老年补贴</span>
          </div>

          <el-form ref="form" label-width="80px">
            <el-form-item label="补贴月份">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>

            <el-form-item>
              <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传xls/xlx文件</div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">导入</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

    </el-row>

  </div>
</template>

<script>


</script>
