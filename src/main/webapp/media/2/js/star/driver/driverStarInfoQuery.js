/**
 * 跳转至驾驶员星级变化详情
 * @param driverId
 * @param orgId
 * @param basePath
 */
function redirectToStarDetail(driverId,orgId,basePath){
	console.log(orgId);
	location.href=basePath+"star/driver/"+orgId+"/"+driverId;
}