/*
 * Copyright 2017-2019 Baidu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "openrasp_ini.h"
#include <regex>
#include <limits>

#define MIN_HEARTBEAT_INTERVAL (10)

Openrasp_ini openrasp_ini;

ZEND_INI_MH(OnUpdateOpenraspCString)
{
    *reinterpret_cast<char **>(mh_arg1) = new_value->val;
    return SUCCESS;
}

ZEND_INI_MH(OnUpdateOpenraspBool)
{
    bool *tmp = reinterpret_cast<bool *>(mh_arg1);
    *tmp = strtobool(new_value->val, new_value->len);
    return SUCCESS;
}

ZEND_INI_MH(OnUpdateOpenraspHeartbeatInterval)
{
    long tmp = zend_atol(new_value->val, new_value->len);
    if (tmp < MIN_HEARTBEAT_INTERVAL || tmp > 1800)
    {
        return FAILURE;
    }
    *reinterpret_cast<int *>(mh_arg1) = tmp;
    return SUCCESS;
}

bool strtobool(const char *str, int len)
{
     return atoi(str);
}