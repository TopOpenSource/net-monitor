import request from '@/utils/request'

// 查询参数列表
export function list(query) {
  return request({
    url: '/poor/dataFile/list',
    method: 'get',
    params: query
  })
}

//保存并上传数据
export function saveAndImport(data) {
  return request({
    url: '/poor/dataFile/saveAndImport',
    method: 'post',
    data: data
  })
}

//删除数据
export function delFileAndRecord(id) {
  return request({
    url: '/poor/dataFile/delFileAndRecord/'+id,
    method: 'delete',
  })
}
