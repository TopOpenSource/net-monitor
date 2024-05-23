import request from '@/utils/request'

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
