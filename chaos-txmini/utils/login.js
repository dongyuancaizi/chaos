var fetch = require('../fetch.js')

function doLogin() {
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    wx.login({
        success: res => {
            fetch.post('wxmini/login', {
                code: res.code
            }, function (data) {
                wx.setStorageSync('wid', data.data.mu)
                wx.setStorageSync('uid', data.data.uid)
                wx.setStorageSync('sessionKey', data.data.token)
                wx.setStorageSync('phone', data.data.phone)
                wx.getLocation({
                    type: 'gcj02',
                    success: function (res) {
                        wx.setStorageSync('addIp', res.longitude + ';' + res.latitude)
                        fetch.post('wxmini/tbl-weixin-user/userupdate', {
                            lat: res.latitude,
                            lng: res.longitude,
                            mu: wx.getStorageSync('wid')
                        }, function (data) {
                            console.log(data)
                        })
                    },
                })
            })
        }
    })
}

module.exports = {
    login: doLogin
}
