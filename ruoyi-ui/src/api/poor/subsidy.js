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


export function selSubsidyList(query) {
  return request({
    url: '/poor/subsidy/selSubsidyList',
    method: 'get',
    params: query
  })
}
