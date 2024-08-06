import request from '@/utils/request'

export function selSubsidyGroupYearType() {
  return request({
    url: '/poor/analysis/selSubsidyGroupYearType',
    method: 'get'
  })
}

export function selUserCountGroupYearType() {
  return request({
    url: '/poor/analysis/selUserCountGroupYearType',
    method: 'get'
  })
}

export function selUserCountGroupByVillage() {
  return request({
    url: '/poor/analysis/selUserCountGroupByVillage',
    method: 'get'
  })
}
