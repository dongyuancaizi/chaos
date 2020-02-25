// pages/authorize/authorize.js
var fetch = require('../../fetch.js')
Page({
    data: {
        isShow: true,
        istrue: 1
    },
    getUserInfo: function (e) {
        var errMsg = e.detail.errMsg
        if (errMsg != 'getUserInfo:ok') return false;
        var userInfo = e.detail.userInfo
        var that = this
        fetch.post('wxmini/tbl-weixin-user/userupdate',
            {
                'nick': userInfo.nickName,
                'head': userInfo.avatarUrl,
                'sex': userInfo.gender,
                'country': userInfo.country,
                'prov': userInfo.province,
                'city': userInfo.city,
                'mu': wx.getStorageSync('wid')
            },
            function (data) {
                that.setData({
                    istrue: 2
                })
            }
        )
    },

    showXy() {
        wx.navigateTo({
            url: '../xy/xy',
        })
    },
    hideWindow: function () {
        this.setData({
            isShow: true
        })
    },
    bindgetphonenumber: function (e) {
        var errMsg = e.detail.errMsg
        if (errMsg != 'getPhoneNumber:ok') return false;
        wx.showLoading({
            title: '处理中',
        })
        fetch.post('wxmini/tbl-weixin-user/binding/phone',
            {
                iv: e.detail.iv,
                encryptedData: e.detail.encryptedData,
                session_key: wx.getStorageSync('sessionKey')
            },
            function (data) {
                wx.hideLoading()
                wx.setStorageSync('phone', data.data)
                wx.showToast({
                    title: '获取成功',
                })
                var page = wx.getStorageSync('page')
                setTimeout(function () {
                    wx.redirectTo({
                        url: '../../' + page,
                    })
                }, 100)
            }
        )
    }

})
