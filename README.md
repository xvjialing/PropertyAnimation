# Android属性动画
> 动画
1. 帧动画是通过将一系列的图片连贯起来播放形成一个视觉上的动画,比如登录的环形进度.但是这会导致需要大量图片,导致apk体积增大.

2. 补间动画通过设置初始值与末值,通过差值器来控制进度.这种方法简单,但只能实现平移,缩放,旋转,透明度这四种动画.而且只是实现了视觉上的变化,实际位置并没有变化

3. 属性动画（Property） 控制属性来实现动画。
   特点：最为强大的动画，弥补了补间动画的缺点，实现位置+视觉的变化。并且可以自定义插值器，实现各种效果