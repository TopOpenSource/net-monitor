import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/poor/subsidy/list',
    method: 'post',
    data:data
  })
}

export function selGroupType(data) {
  return request({
    url: '/poor/subsidy/selGroupType',
    method: 'post',
    data:data
  })
}

export function selGroupYearType(data) {
  return request({
    url: '/poor/subsidy/selGroupYearType',
    method: 'post',
    data:data
  })
}


export function selSubsidyAnalysis(data) {
  return request({
    url: '/poor/subsidy/selSubsidyAnalysis',
    method: 'post',
    data:data
  })
}

export function selSubsidyList(query) {
  return request({
    url: '/poor/subsidy/selSubsidyList',
    method: 'get',
    params: query
  })
}

export function del(ids) {
  return request({
    url: '/poor/subsidy/del/' + ids,
    method: 'delete'
  })
}

export function delNoBind(){
  return request({
    url: '/poor/subsidy/delNoBind',
    method: 'delete'
  })
}

export function update(data) {
  return request({
    url: '/poor/subsidy/update',
    method: 'post',
    data:data
  })
}

export function getInfo(id) {
  return request({
    url: '/poor/subsidy/info/'+id,
    method: 'get',
  })
}


export function selCountBySubsidyType(cardId){
  return request({
    url: '/poor/subsidy/selCountBySubsidyType/'+cardId,
    method: 'get'
  })
}
