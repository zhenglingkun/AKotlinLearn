package com.zlk.akotlinlearn.bean

data class CalenderDayResult(val data: CalenderDayData)

data class CalenderDayData(
    val date: String,// 具体日期(2015-1-1)
    val weekday: String, // 周几(星期四)
    val animalsYear: String, // 属相(马)
    val suit: String, // 宜(订盟.纳采.造车器.祭祀.祈福.出行.安香.修造.动土.上梁.开市.交易.立券.移徙.入宅.会亲友.安机械.栽种.纳畜.造屋.起基.安床.造畜椆栖.)
    val avoid: String, // 忌(破土.安葬.行丧.开生坟.)
    val yearMonth: String, // 年份和月份(2015-1)
    val holiday: String, // 假日(元旦)
    val lunar: String, // 农历(十一月十一)
    val lunarYear: String, // 纪年(甲午年)
    val desc: String // 假日描述(1月1日至3日放假调休，共3天。1月4日（星期日）上班。)
)
