import request from '@/utils/request'
import family from "@/views/poor/family/index.vue";

export function getInfo(id) {
  return request({
    url: '/poor/user/info/'+id,
    method: 'get'
  })
}

// 查询参数列表
export function list(query) {
  return request({
    url: '/poor/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户
export function listAll() {
  return request({
    url: '/poor/user/listAll',
    method: 'get'
  })
}

// 查询家庭成员
export function listFamily(familyId) {
  return request({
    url: '/poor/user/listFamily/'+familyId,
    method: 'get'
  })
}

//保存并上传数据
export function saveOrUpdate(data) {
  return request({
    url: '/poor/user/saveOrUpdate',
    method: 'post',
    data: data
  })
}

//删除数据
export function del(id) {
  return request({
    url: '/poor/user/del/'+id,
    method: 'delete',
  })
}
