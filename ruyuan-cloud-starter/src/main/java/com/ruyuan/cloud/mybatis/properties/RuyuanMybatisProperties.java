/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruyuan.cloud.mybatis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 日志打印配置
 *
 * @author zhonghuashishan
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("ruyuan.mybatis")
public class RuyuanMybatisProperties {
	/**
	 * 是否打印可执行 sql
	 */
	private boolean sql = true;
	/**
	 * 是否开启分页插件
	 */
	private boolean pageEnable = true;

	/**
	 * 是否开启公共字段自动填充的逻辑
	 */
	private boolean autoFill = true;


}
