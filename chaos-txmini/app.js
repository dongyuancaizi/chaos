var login = require('/utils/login.js')
App({
    onLaunch: login.login(),
    up_img: function (data) { //选择上传图片
        wx.chooseImage({
            count: 1,
            sizeType: ['compressed'],
            success: function (res) {
                //上传图片
                wx.showLoading({
                    title: '加载中',
                })
                var url = config.url('stu/index/upImg')
                wx.uploadFile({
                    url: url,
                    filePath: res.tempFilePaths[0],
                    name: 'file',
                    success: function (res) {
                        wx.hideLoading()
                        return typeof data == "function" && data(res)

                    },
                    fail: function (res) {
                        wx.hideLoading()
                        return typeof data == "function" && data(res)
                    }
                })
            },
        })
    },
    /**
     * param string imgUrl 图片相对地址 up_img()返回的url
     */
    delImg: function (imgUrl, cb) { //删除图片

        var url = config.url('stu/index/delImg')
        wx.request({
            url: url,
            method: 'post',
            data: {
                imgUrl
            },
            success: function (res) {
                return typeof cb == 'function' && cb(res.data)
            }
        })


    }
})
