import request from '@/utils/request'

export function getInfo(id) {
  return request({
    url: '/poor/family/info/'+id,
    method: 'get'
  })
}

// 查询参数列表
export function list(query) {
  return request({
    url: '/poor/family/list',
    method: 'get',
    params: query
  })
}




//保存并上传数据
export function saveOrUpdate(data) {
  return request({
    url: '/poor/family/saveOrUpdate',
    method: 'post',
    data: data
  })
}

//删除数据
export function del(id) {
  return request({
    url: '/poor/family/del/'+id,
    method: 'delete',
  })
}
