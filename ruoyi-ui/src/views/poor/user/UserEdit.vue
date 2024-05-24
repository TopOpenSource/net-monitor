<template>
    <el-dialog v-loading="loading" :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body @close="cancel">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"/>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.sys_user_sex"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="出生年月" prop="birthday">
          <el-date-picker
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="身份证号" prop="cardId">
          <el-input v-model="form.cardId"/>
        </el-form-item>

        <el-form-item label="是否残疾" prop="disability">
          <el-select v-model="form.disability" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.yes_no"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="残疾证号" prop="disabilityId" v-if="form.disability=='1'">
          <el-input v-model="form.disabilityId"/>
        </el-form-item>

        <el-form-item label="是否死亡" prop="live">
          <el-select v-model="form.live" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.yes_no"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone"/>
        </el-form-item>

        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


</template>

<script>
import {saveOrUpdate} from "@/api/poor/user";

export default {
  name: "PoorUserEdit",
  dicts: ['yes_no', 'sys_user_sex'],
  props:{
    form:{

    },
    openView:{
      default:false
    },
    title:{

    }
  },
  watch:{
    openView(value){
      this.open=value
    }
  },
  data() {
    return {
      open:false,
      loading:false,
      // 表单校验
      rules: {
        name: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        sex: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        birthday: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        cardId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        disability: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        disabilityId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        live: [
          {required: true, message: "不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {

  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
      this.$emit("cancelOpt")
    },

    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          saveOrUpdate(this.form).then(response => {
               this.$emit("success")
          })
        }
      });
    },
  }
};
</script>
