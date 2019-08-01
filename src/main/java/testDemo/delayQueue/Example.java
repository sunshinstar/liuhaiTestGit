package testDemo.delayQueue;

/**
 * @author liuhai
 * @date 2019/6/10 8:48
 */
public class Example {
    /***　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
     *　　　　　　　　　瓦瓦　　　　　　　　　　　　十　　　　　　　　　　　　　
     *　　　　　　　　十齱龠己　　　　　　　　　亅瓦車己　　　　　　　　　　　　
     *　　　　　　　　乙龍龠毋日丶　　　　　　丶乙己毋毋丶　　　　　　　　　　　
     *　　　　　　　　十龠馬鬼車瓦　　　　　　己十瓦毋毋　　　　　　　　　　　　
     *　　　　　　　　　鬼馬龠馬龠十　　　　己己毋車毋瓦　　　　　　　　　　　　
     *　　　　　　　　　毋龠龠龍龠鬼乙丶丶乙車乙毋鬼車己　　　　　　　　　　　　
     *　　　　　　　　　乙龠龍龍鬼龍瓦　十瓦毋乙瓦龠瓦亅　　　　　　　　　　　　
     *　　　　　　　　　　馬齱龍馬鬼十丶日己己己毋車乙丶　　　　　　　　　　　　
     *　　　　　　　　　　己齱馬鬼車十十毋日乙己己乙乙　　　　　　　　　　　　　
     *　　　　　　　　　　　車馬齱齱日乙毋瓦己乙瓦日亅　　　　　　　　　　　　　
     *　　　　　　　　　　　亅車齺龖瓦乙車龖龍乙乙十　　　　　　　　　　　　　　
     *　　　　　　　　　　　　日龠龠十亅車龍毋十十　　　　　　　　　　　　　　　
     *　　　　　　　　　　　　日毋己亅　己己十亅亅　　　　　　　　　　　　　　　
     *　　　　　　　　　　　丶己十十乙　　丶丶丶丶丶　　　　　　　　　　　　　　
     *　　　　　　　　　　　亅己十龍龖瓦　　丶　丶　乙十　　　　　　　　　　　　
     *　　　　　　　　　　　亅己十龠龖毋　丶丶　　丶己鬼鬼瓦亅　　　　　　　　　
     *　　　　　　　　　　　十日十十日亅丶亅丶　丶十日毋鬼馬馬車乙　　　　　　　
     *　　　　　　　　　　　十日乙十亅亅亅丶　　十乙己毋鬼鬼鬼龍齺馬乙　　　　　
     *　　　　　　　　　　　丶瓦己乙十十亅丶亅乙乙乙己毋鬼鬼鬼龍齱齺齺鬼十　　　
     *　　　　　　　　　　　　乙乙十十十亅乙瓦瓦己日瓦毋鬼鬼龠齱齱龍龍齱齱毋丶　
     *　　　　　　　　　　　　亅十十十十乙瓦車毋瓦瓦日車馬龠龍龍龍龍龍龠龠龠馬亅
     *　　　　　　　　　　　　　十十十十己毋車瓦瓦瓦瓦鬼馬龠龍龠龠龍龠龠龠馬龠車
     *　　　　　　　　　　　　　　亅十十日毋瓦日日瓦鬼鬼鬼龠龠馬馬龠龍龍龠馬馬車
     *　　　　　　　　　　　　　　亅亅亅乙瓦瓦毋車車車馬龍龠鬼鬼馬龠龍龍龠馬馬鬼
     *　　　　　　　　　　　　丶丶乙亅亅乙車鬼鬼鬼毋車龍龍龠鬼馬馬龠龍齱齱龍馬鬼
     *　　　　　　　　　　　亅己十十己十日鬼鬼車瓦毋龠龍龠馬馬龠龠龠齱齺齺齱龠鬼
     *　　　　　　　　　　　　亅乙乙乙十車馬車毋馬齱齱龍龠龠龠馬龠龍齱龍龠龠鬼瓦
     *　　　　　　　　　　　　　　　　丶毋龠鬼車瓦車馬龠龍龠龠龍齱齱龠馬馬鬼毋日
     *　　　　　　　　　　　　　　　　十乙己日十　　丶己鬼龍齱齺齱龍馬馬馬車毋己
     *　　　　　　　　　　　　　　丶十己乙亅丶　　　　　　亅瓦馬龠龍龠龠馬毋瓦乙
     *　　　　　　　　　　　　　丶十十乙亅十　　　　　　　　亅己瓦車馬龠鬼車瓦乙
     *　　　　　　　　　　　　　丶十乙十十丶　　　　　　　　　丶丶亅十瓦鬼車瓦己
     *　　　　　　　　　　　　　　丶亅亅丶　　　　　　　　　　　　　　　亅日瓦日
     *　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　丶
     */


/***
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
 * │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
 */

/***
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */

/***
 *  佛曰:
 *          写字楼里写字间，写字间里程序员；
 *          程序人员写程序，又拿程序换酒钱。
 *          酒醒只在网上坐，酒醉还来网下眠；
 *          酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前；
 *          奔驰宝马贵者趣，公交自行程序员。
 *          别人笑我忒疯癫，我笑自己命太贱；
 *          不见满街漂亮妹，哪个归得程序员？
 */

/***
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 *  O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 *          .............................................
 *           佛曰：bug泛滥，我已瘫痪！
 */

/***
 *
 *   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 *  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 *  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 *  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 *           ░     ░ ░      ░  ░
 */

/***
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  FUCK YOU
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 */

/***
 *      ┌─┐       ┌─┐
 *   ┌──┘ ┴───────┘ ┴──┐
 *   │                 │
 *   │       ───       │
 *   │  ─┬┘       └┬─  │
 *   │                 │
 *   │       ─┴─       │
 *   │                 │
 *   └───┐         ┌───┘
 *       │         │
 *       │         │
 *       │         │
 *       │         └──────────────┐
 *       │                        │
 *       │                        ├─┐
 *       │                        ┌─┘
 *       │                        │
 *       └─┐  ┐  ┌───────┬──┐  ┌──┘
 *         │ ─┤ ─┤       │ ─┤ ─┤
 *         └──┴──┘       └──┴──┘
 *                神兽保佑
 *               代码无BUG!
 */

/***
 *                  ___====-_  _-====___
 *            _--^^^#####//      \\#####^^^--_
 *         _-^##########// (    ) \\##########^-_
 *        -############//  |\^^/|  \\############-
 *      _/############//   (@::@)   \\############\_
 *     /#############((     \\//     ))#############\
 *    -###############\\    (oo)    //###############-
 *   -#################\\  / VV \  //#################-
 *  -###################\\/      \//###################-
 * _#/|##########/\######(   /\   )######/\##########|\#_
 * |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 * `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *    `   `  `      `   / | |  | | \   '      '  '   '
 *                     (  | |  | |  )
 *                    __\ | |  | | /__
 *                   (vvv(VVV)(VVV)vvv)
 *                        神兽保佑
 *                       代码无BUG!
 */

/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 */

/***                              _
 *  _._ _..._ .-',     _.._(`))
 * '-. `     '  /-._.-'    ',/
 *    )         \            '.
 *   / _    _    |             \
 *  |  a    a    /              |
 *  \   .-.                     ;
 *   '-('' ).-'       ,'       ;
 *      '-;           |      .'
 *         \           \    /
 *         | 7  .__  _.-\   \
 *         | |  |  ``/  /`  /
 *        /,_|  |   /,_/   /
 *           /,_/      '`-'
 */

/***
 **************************************************************
 *                                                            *
 *   .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.       *
 *    |                     ______                     |      *
 *    |                  .-"      "-.                  |      *
 *    |                 /            \                 |      *
 *    |     _          |              |          _     |      *
 *    |    ( \         |,  .-.  .-.  ,|         / )    |      *
 *    |     > "=._     | )(__/  \__)( |     _.=" <     |      *
 *    |    (_/"=._"=._ |/     /\     \| _.="_.="\_)    |      *
 *    |           "=._"(_     ^^     _)"_.="           |      *
 *    |               "=\__|IIIIII|__/="               |      *
 *    |              _.="| \IIIIII/ |"=._              |      *
 *    |    _     _.="_.="\          /"=._"=._     _    |      *
 *    |   ( \_.="_.="     `--------`     "=._"=._/ )   |      *
 *    |    > _.="                            "=._ <    |      *
 *    |   (_/                                    \_)   |      *
 *    |                                                |      *
 *    '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *
 *                                                            *
 *           LASCIATE OGNI SPERANZA, VOI CH'ENTRATE           *
 **************************************************************
 */

/***
 *                                         ,s555SB@@&
 *                                      :9H####@@@@@Xi
 *                                     1@@@@@@@@@@@@@@8
 *                                   ,8@@@@@@@@@B@@@@@@8
 *                                  :B@@@@X3hi8Bs;B@@@@@Ah,
 *             ,8i                  r@@@B:     1S ,M@@@@@@#8;
 *            1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S
 *            1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5
 *            ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A
 *             1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1
 *              sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1
 *               .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1
 *               s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981
 *               ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;
 *                .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS
 *                s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,
 *              :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,
 *            .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8
 *          :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:
 *      s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r
 *   ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i
 *  5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;
 *  9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:
 *   SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5
 *     ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B
 *           ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S
 *              ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:
 *            .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
 *
 *
 *    又看源码，看你妹妹呀！
 */

/***
 *_______________#########_______________________
 *______________############_____________________
 *______________#############____________________
 *_____________##__###########___________________
 *____________###__######_#####__________________
 *____________###_#######___####_________________
 *___________###__##########_####________________
 *__________####__###########_####_______________
 *________#####___###########__#####_____________
 *_______######___###_########___#####___________
 *_______#####___###___########___######_________
 *______######___###__###########___######_______
 *_____######___####_##############__######______
 *____#######__#####################_#######_____
 *____#######__##############################____
 *___#######__######_#################_#######___
 *___#######__######_######_#########___######___
 *___#######____##__######___######_____######___
 *___#######________######____#####_____#####____
 *____######________#####_____#####_____####_____
 *_____#####________####______#####_____###______
 *______#####______;###________###______#________
 *________##_______####________####______________
 */

/***
 * http://www.freebuf.com/
 *           _.._        ,------------.
 *        ,'      `.    ( We want you! )
 *       /  __) __` \    `-,----------'
 *      (  (`-`(-')  ) _.-'
 *      /)  \  = /  (
 *     /'    |--' .  \
 *    (  ,---|  `-.)__`
 *     )(  `-.,--'   _`-.
 *    '/,'          (  Uu",
 *     (_       ,    `/,-' )
 *     `.__,  : `-'/  /`--'
 *       |     `--'  |
 *       `   `-._   /
 *        \        (
 *        /\ .      \.  freebuf
 *       / |` \     ,-\
 *      /  \| .)   /   \
 *     ( ,'|\    ,'     :
 *     | \,`.`--"/      }
 *     `,'    \  |,'    /
 *    / "-._   `-/      |
 *    "-.   "-.,'|     ;
 *   /        _/["---'""]
 *  :        /  |"-     '
 *  '           |      /
 *              `      |
 */

    /***
     * http://www.flvcd.com/
     *  .--,       .--,
     * ( (  \.---./  ) )
     *  '.__/o   o\__.'
     *     {=  ^  =}
     *      >  -  <
     *     /       \
     *    //       \\
     *   //|   .   |\\
     *   "'\       /'"_.-~^`'-.
     *      \  _  /--'         `
     *    ___)( )(___
     *   (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。
     */




/***
 * 这个公司没有年终奖的,兄弟别指望了,也别来了,我准备辞职了
 * 另外这个项目有很多*Bug* 你坚持不了多久的,拜拜!
 */

/**
 * 1只羊 == one sheep
 * 2只羊 == two sheeps
 * 3只羊 == three sheeps
 * 4只羊 == four sheeps
 * 5只羊 == five sheeps
 * 6只羊 == six sheeps
 * 7只羊 == seven sheeps
 * 8只羊 == eight sheeps
 * 9只羊 == nine sheeps
 * 10只羊 == ten sheeps
 * 11只羊 == eleven sheeps
 * 12只羊 == twelve sheeps
 * 13只羊 == thirteen sheeps
 * 14只羊 == fourteen sheeps
 * 15只羊 == fifteen sheeps
 * 16只羊 == sixteen sheeps
 * 17只羊 == seventeen sheeps
 * 18只羊 == eighteen sheeps
 * 19只羊 == nineteen sheeps
 * 20只羊 == twenty sheeps
 * 21只羊 == twenty one sheeps
 * 22只羊 == twenty two sheeps
 * 23只羊 == twenty three sheeps
 * 24只羊 == twenty four sheeps
 * 25只羊 == twenty five sheeps
 * 26只羊 == twenty six sheeps
 * 27只羊 == twenty seven sheeps
 * 28只羊 == twenty eight sheeps
 * 29只羊 == twenty nine sheeps
 * 30只羊 == thirty sheeps
 * 现在瞌睡了吧，好了，不要再改下面的代码了，睡觉咯~~
 */

/***
 * 程序员1（于2010年6月7日）：在这个坑临时加入一些调料
 * 程序员2（于2011年5月22日）：临你个屁啊
 * 程序员3（于2012年7月23日）：楼上都是狗屎，鉴定完毕
 * 程序员4（于2013年8月2日）：fuck 楼上，三年了，这坑还在！！！
 * 程序员5（于2014年8月21日）：哈哈哈，这坑居然坑了这么多人，幸好我也不用填了，系统终止运行了，you're died
 */

/***
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 */
/***
 * 致终于来到这里的勇敢的人：
 * 你是被上帝选中的人，是英勇的、不敌辛苦的、不眠不休的来修改我们这最棘手的代码的编程骑士。
 * 你，我们的救世主，人中之龙，我要对你说：永远不要放弃，永远不要对自己失望，永远不要逃走，辜负了自己，
 * 永远不要哭啼，永远不要说再见，永远不要说谎来伤害自己。
 */

/***
 * Dear maintainer:
 *
 * Once you are done trying to 'optimize' this routine,
 * and have realized what a terrible mistake that was,
 * please increment the following counter as a warning
 * to the next guy:
 *
 * total_hours_wasted_here = 42
 */
/***
 * 亲爱的维护者：
 *
 * 如果你尝试了对这段程序进行'优化'
 * 下面这个计数器的个数用来对后来人进行警告
 *
 * 浪费在这里的总时间 = 42h
 */

/***
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 */
/***
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */


// Autogenerated, do not edit. All changes will be undone.

// sometimes I believe compiler ignores all my comments
// 有时候我相信编译器忽略了我所有的注释

/***
 * I dedicate all this code, all my work, to my wife, Darlene, who will
 * have to support me and our three children and the dog once it gets
 * released into the public.
 */

// drunk, fix later
// 有点晕了，以后再修改

// Magic. Do not touch.
// 麻鸡。勿动。
public static void main(String[] args) {
    System.out.println("            _.._        ,------------.\n" +
            "        ,'      `.    ( We want you! )\n" +
            "        /  __) __` \\    `-,----------'\n" +
            "      (  (`-`(-')  ) _.-'\n" +
            "       /)  \\  = /  (\n" +
            "      /'    |--' .  \\\n" +
            "     (  ,---|  `-.)__`\n" +
            "      )(  `-.,--'   _`-.\n" +
            "     '/,'          (  Uu\",\n" +
            "     (_       ,    `/,-' )\n" +
            "      `.__,  : `-'/  /`--'\n" +
            "        |     `--'  |\n" +
            "        `   `-._   /\n" +
            "         \\        (\n" +
            "         /\\ .      \\.  freebuf\n" +
            "        / |` \\     ,-\\\n" +
            "       /  \\| .)   /   \\\n" +
            "      ( ,'|\\    ,'     :\n" +
            "      | \\,`.`--\"/      }\n" +
            "      `,'    \\  |,'    /\n" +
            "     / \"-._   `-/      |\n" +
            "    \"-.   \"-.,'|     ;\n" +
            "    /        _/[\"---'\"\"]\n" +
            "   :        /  |\"-     '\n" +
            "   '           |      /\n" +
            "               `      |");
}
}