var fetch = require('../../fetch.js')
var check = require('../../utils/check.js')
Page({
    data: {
        list: []
    },
    onLoad: function () {
        wx.setStorageSync('page', 'pages/index/index')
        wx.showLoading({
            title: '加载中',
        })
        var that = this
        fetch.post('wxmini/tbl-iya-info/infolist', {}, function (data) {
            wx.hideLoading()
            that.setData({
                list: data.page.list
            })
        })
    },
    onShareAppMessage: function () {
        return {
            title: "矮芽-医生资质查询",
            path: "pages/index/index"
        }
    },
    goDocterlist: function () {
        check.phone();
        wx.navigateTo({
            url: '../docterlist/docterlist',
        })
    },
    goCity() {
        check.phone();
        wx.navigateTo({
            url: '../citylist/citylist',
        })
    },
    goBooklist() {
        check.phone();
        wx.navigateTo({
            url: '../booklist/booklist',
        })
    },
    goWeb: function (e) {
        var id = e.currentTarget.dataset.id
        var list = this.data.list
        wx.navigateTo({
            url: '../web/web?twid=' + list[id]['mu']
        })
    }

})
